package com.st.jombaykotlinassignment

import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.st.jombaykotlinassignment.adapter.UserAdapter
import com.st.jombaykotlinassignment.base.BaseActivity
import com.st.jombaykotlinassignment.databinding.ActivityMainBinding
import com.st.jombaykotlinassignment.model.UserModel
import com.st.jombaykotlinassignment.network.ApiClient
import com.st.jombaykotlinassignment.network.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : BaseActivity() {
    override val bindingInflater: (LayoutInflater) -> ViewBinding = ActivityMainBinding::inflate
    override val binding: ActivityMainBinding get() = super.binding as ActivityMainBinding
    private var visibleItemCount: Int = 0
    private var totalItemCount: Int = 0
    private var pastVisibleItems: Int = 0
    private var loading = true
    private var userAdapter = UserAdapter()

    override fun startWorking() {
        binding.apply {
            rvList.apply {
                layoutManager = LinearLayoutManager(root.context)
                adapter = userAdapter
                addOnScrollListener(object : RecyclerView.OnScrollListener() {
                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                        val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                        visibleItemCount = recyclerView.childCount
                        totalItemCount = layoutManager.itemCount
                        pastVisibleItems = layoutManager.findFirstVisibleItemPosition()

                        if (loading) {
                            if ((visibleItemCount + pastVisibleItems) == totalItemCount) {
                                loading = false
                            }
                        }

                        if (!loading && (visibleItemCount + pastVisibleItems) == totalItemCount) {
                            var offset = 0
                            offset = userAdapter.listUser.size

                            loadDataFromApi(offset)
                        }
                    }
                })
            }
        }

        this.loadDataFromApi(0)
    }

    private fun loadDataFromApi(offset: Int) {
        val apiInterface: ApiInterface = ApiClient.getApiInterface()
        val param = HashMap<String, Int>()
        param.put("offset", offset)
        param.put("limit", 10)
        val call = apiInterface.fetchUser(param)
        call.enqueue(object : Callback<List<UserModel>> {
            override fun onResponse(
                call: Call<List<UserModel>>,
                response: Response<List<UserModel>>
            ) {
                if (response.isSuccessful) {
                    userAdapter.setListUser(response.body() as MutableList<UserModel>)
                } else {
                    toast("Something went wrong..")
                }
            }

            override fun onFailure(call: Call<List<UserModel>>, t: Throwable) {
                toast(t.message)
            }

        })
    }

}
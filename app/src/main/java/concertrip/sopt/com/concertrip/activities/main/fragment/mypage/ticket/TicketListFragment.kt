package concertrip.sopt.com.concertrip.activities.main.fragment.mypage.ticket

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import concertrip.sopt.com.concertrip.R
import concertrip.sopt.com.concertrip.interfaces.OnFragmentInteractionListener
import concertrip.sopt.com.concertrip.interfaces.OnResponse
import concertrip.sopt.com.concertrip.list.adapter.BasicListAdapter
import concertrip.sopt.com.concertrip.list.adapter.TicketListAdapter
import concertrip.sopt.com.concertrip.model.Ticket
import concertrip.sopt.com.concertrip.network.ApplicationController
import concertrip.sopt.com.concertrip.network.NetworkService
import concertrip.sopt.com.concertrip.network.response.GetTicketListResponse
import concertrip.sopt.com.concertrip.network.response.data.TicketData
import concertrip.sopt.com.concertrip.network.response.interfaces.BaseModel
import concertrip.sopt.com.concertrip.utillity.NetworkUtil.Companion.getTicketList
import kotlinx.android.synthetic.main.fragment_my_page.*
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.fragment_ticket_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [TicketListFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [TicketListFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class TicketListFragment : Fragment() , OnFragmentInteractionListener, OnResponse{
    var dataListTicket = arrayListOf<Ticket>()
    lateinit var ticketAdapter : TicketListAdapter

    private val networkServicce : NetworkService by lazy {
        ApplicationController.instance.networkService
    }

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null

    var dataList = arrayListOf<Ticket>()

    lateinit var Adapter : BasicListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }


            }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ticket_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialUI()
        ConnectRequestData()
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    private fun initialUI(){

        btn_ticket_list_back.setOnClickListener {
            activity?.onBackPressed()
        }


        activity?.let{
            //dataListTicket = Ticket.getDummyArray()
            ticketAdapter = TicketListAdapter(it.applicationContext, dataListTicket, this)
            recycler_view_ticket_list.adapter = ticketAdapter
        }
    }


    override fun onFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun changeFragment(what: Int) {
        listener?.changeFragment(what)
    }

    override fun changeFragment(what: Int, bundle: Bundle?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun updateUI(){

    }

    private fun updateListTicket(list : ArrayList<Ticket>){
        dataListTicket.clear()
        dataListTicket.addAll(list)
        ticketAdapter.notifyDataSetChanged()
    }

    private fun ConnectRequestData(){
        getTicketList(networkServicce, this, "")
    }

    override fun onSuccess(obj: BaseModel, position: Int?) {
        if(obj is GetTicketListResponse){
            var responseBody = obj as GetTicketListResponse

            responseBody?.let{
                if(it.status  == 200){
                    val ticketList = it.toTicketList()
                    updateListTicket(ticketList)}
                else{
                    Log.d("testTicket", "getTicketListResponse in" + responseBody?.status.toString())
                }
            }
        }
    }

    override fun onFail() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        Log.d("testTicket", "getTicketListResponse in onFailure ")
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TicketListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TicketListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}

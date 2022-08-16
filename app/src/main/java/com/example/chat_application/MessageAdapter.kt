package com.example.chat_application

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth

class MessageAdapter (val context : Context, val messageList: ArrayList<Message> ): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val ITEM_SENT = 2
    val ITEM_RECIEVE = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return if(viewType == 1)
            SendViewHolder(LayoutInflater.from(context).inflate(R.layout.receive, parent, false))
        else{
            ReceiveViewHolder(LayoutInflater.from(context).inflate(R.layout.send, parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentMessage = messageList[position]

        Log.d("Sent Message","${currentMessage.message}")

        if(holder.javaClass == SendViewHolder::class.java){

            //for sent message
            val viewHolder = holder as SendViewHolder
            holder.sendMessage?.text= currentMessage.message

        }else{
            //for receive message
            val viewHolder = holder as ReceiveViewHolder
            holder.receiveMessage?.text= currentMessage.message
        }
    }

    override fun getItemCount(): Int {
        return messageList.size
    }

    override fun getItemViewType(position: Int): Int {

        val currentMessage = messageList[position]

        Log.d("UID","${FirebaseAuth.getInstance().currentUser?.uid}")
        Log.d("senderID","${FirebaseAuth.getInstance().uid}")

        return if(FirebaseAuth.getInstance().currentUser?.uid.equals(FirebaseAuth.getInstance().uid)){
            ITEM_SENT
        }else{
            ITEM_RECIEVE
        }
    }

    class SendViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
 val sendMessage = itemView.findViewById<TextView>(R.id.sent_message)
    }

    class ReceiveViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val receiveMessage = itemView.findViewById<TextView>(R.id.received_message)

    }
}
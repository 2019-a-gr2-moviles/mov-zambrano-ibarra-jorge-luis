package com.example.deberrecyclerview
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.cometchat.pro.models.BaseMessage
import com.cometchat.pro.models.TextMessage
import com.example.deberrecyclerview.R

class MessagesAdapter(private val uid: String,
                      private var messages: MutableList<BaseMessage>)  : RecyclerView.Adapter<MessagesAdapter.MessageViewHolder>() {
    companion object {
        private const val SENT = 0
        private const val RECEIVED = 1
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {

        val view = when (viewType) {
            SENT -> {
                LayoutInflater.from(parent.context).inflate(R.layout.item_sent, parent, false)
            }
            else -> {
                LayoutInflater.from(parent.context).inflate(R.layout.item_received, parent, false)
            }
        }
        return MessageViewHolder(view)
    }
    override fun getItemCount() = messages.size
    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        holder.bind(messages[position])
    }
    override fun getItemViewType(position: Int): Int {
        return if (messages[position].sender?.uid!!.contentEquals(uid)) {
            SENT
        } else {
            RECEIVED
        }
    }
    fun updateMessages(messages: List<BaseMessage>) {
        this.messages = messages.toMutableList()
        notifyDataSetChanged()
    }
    fun appendMessage(message: BaseMessage) {
        this.messages.add(message)
        notifyItemInserted(this.messages.size - 1)
    }
    inner class MessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val messageText: TextView = itemView.findViewById(R.id.message_text)
        var estadoLike:Boolean=false
        var estadoFase:Boolean=false
        var estadoglobal:Boolean=false
        var accionButton: Button= itemView.findViewById(R.id.btnLike) as Button
        var favButton: Button= itemView.findViewById(R.id.btnFav) as Button
        init{

            accionButton.setOnClickListener {
                if (estadoLike == false) {
                    messageText.setTextColor(Color.RED)
                    messageText.setCompoundDrawablesWithIntrinsicBounds(R.drawable.love, 0, 0, 0);
                    estadoLike = true
                    estadoFase=false


                } else {
                    messageText.setTextColor(Color.BLACK)
                    messageText.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);4
                    estadoLike = false
                }
            }

            favButton.setOnClickListener {
                if (estadoFase == false) {
                    val orange = Color.rgb(255, 165, 0)
                    messageText.setTextColor(orange)
                    messageText.setCompoundDrawablesWithIntrinsicBounds(R.drawable.star, 0, 0, 0);
                    estadoFase = true
                    estadoLike=false
                }
                else {
                    messageText.setTextColor(Color.BLACK)
                    messageText.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);4
                    estadoFase=false
                }


            }
        }

        fun bind(message: BaseMessage) {
            if (message is TextMessage) {
                messageText.text = message.text
            }
        }
    }
}
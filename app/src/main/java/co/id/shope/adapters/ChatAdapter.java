package co.id.shope.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


import de.hdodenhof.circleimageview.CircleImageView;
import co.id.shope.R;
import co.id.shope.data.Session;
import co.id.shope.models.chat.DataItemChat;

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int VIEW_TYPE_USER_MESSAGE = 0;
    public static final int VIEW_TYPE_FRIEND_MESSAGE = 1;

    Session session;
    public interface OnItemClick {
        
    }

    public OnItemClick onItemClick;

    private List<DataItemChat> items;
    private Context ctx;

    public ChatAdapter(Context context) {
        this.items = new ArrayList<>();
        ctx = context;
        session = new Session(context);
    }

    class ItemMessageUserHolder extends RecyclerView.ViewHolder {
        public TextView txtContent;
        public CircleImageView avata;

        public ItemMessageUserHolder(View itemView) {
            super(itemView);
            txtContent = (TextView) itemView.findViewById(R.id.textContentUser);
            avata = (CircleImageView) itemView.findViewById(R.id.imageView2);
        }
    }

    class ItemMessageFriendHolder extends RecyclerView.ViewHolder {
        public TextView txtContent;
        public CircleImageView avata;

        public ItemMessageFriendHolder(View itemView) {
            super(itemView);
            txtContent = (TextView) itemView.findViewById(R.id.textContentFriend);
            avata = (CircleImageView) itemView.findViewById(R.id.imageView3);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_FRIEND_MESSAGE) {
            View view = LayoutInflater.from(ctx).inflate(R.layout.rc_item_message_friend, parent, false);
            return new ItemMessageFriendHolder(view);
        } else if (viewType == VIEW_TYPE_USER_MESSAGE) {
            View view = LayoutInflater.from(ctx).inflate(R.layout.rc_item_message_user, parent, false);
            return new ItemMessageUserHolder(view);
        }
        return null;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        DataItemChat chat = items.get(position);
        if (holder instanceof ItemMessageFriendHolder) {
            ((ItemMessageFriendHolder) holder).txtContent.setText(chat.getPesan());

        } else if (holder instanceof ItemMessageUserHolder) {
            ((ItemMessageUserHolder) holder).txtContent.setText(chat.getPesan());

        }
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position).getIdUser() == session.getUser().getData().getId() ? VIEW_TYPE_USER_MESSAGE : VIEW_TYPE_FRIEND_MESSAGE;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void add(DataItemChat dataItem) {
        items.add(dataItem);
        notifyItemInserted(items.size() + 1);
    }

    public void addAll(List<DataItemChat> items) {
        for (DataItemChat rapatK3 : items) {
            add(rapatK3);
        }
    }

    public void remove(DataItemChat item) {
        int position = items.indexOf(item);
        if (position > -1) {
            items.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void swap(List<DataItemChat> datas) {
        if (datas == null || datas.size() == 0)
            return;
        if (items != null && items.size() > 0)
            items.clear();
        items.addAll(datas);
        notifyDataSetChanged();

    }

    public DataItemChat getItem(int pos) {
        return items.get(pos);
    }

    public OnItemClick getOnItemClick() {
        return onItemClick;
    }

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }
}
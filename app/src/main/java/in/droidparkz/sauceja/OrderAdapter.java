package in.droidparkz.sauceja;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {
    private Context mContext;
    private ArrayList<OrderItem> mOrderList;

    public OrderAdapter(Context context, ArrayList<OrderItem> OrderList) {
        mContext = context;
        mOrderList = OrderList;
    }

    @Override
    public OrderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.orderitem, parent, false);
        return new OrderViewHolder(v);
    }

    @Override
    public void onBindViewHolder(OrderViewHolder holder, int position) {
        OrderItem currentItem = mOrderList.get(position);

        String name = currentItem.getName();
        String quantity = currentItem.getQuantity();
        String price = currentItem.getPrice();

        holder.mTextViewName.setText("Name : "+name);
        holder.mTextViewQuantity.setText("Quantity : "+quantity);
        holder.mTextViewPrice.setText("Price : "+price);

    }

    @Override
    public int getItemCount() {

        return mOrderList.size();
    }

    public class OrderViewHolder extends RecyclerView.ViewHolder  {

        public TextView mTextViewName,mTextViewQuantity,mTextViewPrice;

        public OrderViewHolder(View itemView) {
            super(itemView);

            mTextViewName = itemView.findViewById(R.id.ordername);
            mTextViewQuantity = itemView.findViewById(R.id.orderquantity);
            mTextViewPrice = itemView.findViewById(R.id.orderprice);

        }
    }
}


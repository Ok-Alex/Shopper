package me.akulakovsky.shopper.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.text.DecimalFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.akulakovsky.shopper.R;
import me.akulakovsky.shopper.entities.Product;
import me.akulakovsky.shopper.entities.responses.SimilarProductsResponse;
import me.akulakovsky.shopper.entities.responses.SingleProductResponse;

/**
 * Created by akulakovsky on 9/12/17.
 */

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductHolder> {

    private Context context;
    private List<SingleProductResponse> data;
    private DecimalFormat decimalFormat = new DecimalFormat("#.##");
    private Transformation transformation;

    public ProductAdapter(Context context, List<SingleProductResponse> data) {
        this.context = context;
        this.data = data;
        this.transformation = new RoundedTransformationBuilder()
                .borderWidthDp(0)
                .cornerRadiusDp(15)
                .scaleType(ImageView.ScaleType.CENTER_CROP)
                .oval(false)
                .build();
    }

    @Override
    public ProductHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_product, parent ,false);
        return new ProductHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductHolder holder, int position) {
        Product product = data.get(position).getProduct();

        holder.mTitle.setText(product.getName());
        holder.mPrice.setText(Html.fromHtml("<s>" + "$" + decimalFormat.format(Double.parseDouble(product.getPrice())) + "</s>"));

        double price = Double.parseDouble(product.getPrice());
        double salePrice = Double.parseDouble(product.getSalePrice());
        int percent = (int) (100 * salePrice / price);

        Picasso.with(holder.mImage.getContext())
                .load(product.getPrimaryPhotos().get(0).getWebUrl())
                .fit()
                .transform(transformation)
                .into(holder.mImage);

        holder.mSalePrice.setText("$" + decimalFormat.format(Double.parseDouble(product.getSalePrice())) + ", " + percent + "% off");

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ProductHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.title) TextView mTitle;
        @BindView(R.id.price) TextView mPrice;
        @BindView(R.id.salePrice) TextView mSalePrice;
        @BindView(R.id.image) ImageView mImage;

        public ProductHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

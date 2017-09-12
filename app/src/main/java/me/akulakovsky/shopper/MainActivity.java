package me.akulakovsky.shopper;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.akulakovsky.shopper.adapters.ProductAdapter;
import me.akulakovsky.shopper.adapters.ProductImagesAdapter;
import me.akulakovsky.shopper.entities.Product;
import me.akulakovsky.shopper.entities.ProductPhoto;
import me.akulakovsky.shopper.entities.ProductSize;
import me.akulakovsky.shopper.entities.responses.SimilarProductsResponse;
import me.akulakovsky.shopper.entities.responses.SingleProductResponse;
import me.akulakovsky.shopper.utils.Api;
import me.akulakovsky.shopper.utils.SpacesItemDecoration;
import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.indicator) CircleIndicator mIndicator;
    @BindView(R.id.pager) ViewPager mPager;

    @BindView(R.id.title) TextView mTitle;
    @BindView(R.id.description) TextView mDescription;
    @BindView(R.id.price) TextView mPrice;
    @BindView(R.id.salePrice) TextView mSalePrice;
    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;
    @BindView(R.id.sizes) LinearLayout mSizes;

    private DecimalFormat decimalFormat = new DecimalFormat("#.##");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Api.getInstance().getSingleProduct().enqueue(new Callback<SingleProductResponse>() {
            @Override
            public void onResponse(Call<SingleProductResponse> call, Response<SingleProductResponse> response) {
                if (response.isSuccessful()) {
                    SingleProductResponse productResponse = response.body();
                    fillProductUI(productResponse.getProduct());
                }
            }

            @Override
            public void onFailure(Call<SingleProductResponse> call, Throwable t) {

            }
        });

        Api.getInstance().getSimilarProducts().enqueue(new Callback<SimilarProductsResponse>() {
            @Override
            public void onResponse(Call<SimilarProductsResponse> call, Response<SimilarProductsResponse> response) {
                if (response.isSuccessful()) {
                    SimilarProductsResponse similarProductsResponse = response.body();
                    fillSimilarProductsUI(similarProductsResponse.getSimilarProducts());
                }
            }

            @Override
            public void onFailure(Call<SimilarProductsResponse> call, Throwable t) {

            }
        });
    }

    private void fillSimilarProductsUI(List<SingleProductResponse> productList) {
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        GridLayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new SpacesItemDecoration(2, 10, false));

        // specify an adapter (see also next example)
        ProductAdapter mAdapter = new ProductAdapter(this, productList);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void fillProductUI(Product product) {
        List<ProductPhoto> photos = product.getPrimaryPhotos();
        photos.addAll(product.getSecondaryPhotos());
        ProductImagesAdapter adapter = new ProductImagesAdapter(this, photos);
        mPager.setAdapter(adapter);
        mIndicator.setViewPager(mPager);

        mTitle.setText(product.getName());
        mDescription.setText(product.getDescription());
        mPrice.setText(Html.fromHtml("<s>" + "$" + decimalFormat.format(Double.parseDouble(product.getPrice())) + "</s>"));

        double price = Double.parseDouble(product.getPrice());
        double salePrice = Double.parseDouble(product.getSalePrice());
        int percent = (int) (100 * salePrice / price);

        mSalePrice.setText("$" + decimalFormat.format(Double.parseDouble(product.getSalePrice())) + ", " + percent + "% off");

        mSizes.removeAllViews();
        for (ProductSize productSize: product.getSizes()) {
            TextView sizeView = (TextView) getLayoutInflater().inflate(R.layout.list_item_size, mSizes, false);
            sizeView.setText(productSize.getName());
            mSizes.addView(sizeView);
        }
    }
}

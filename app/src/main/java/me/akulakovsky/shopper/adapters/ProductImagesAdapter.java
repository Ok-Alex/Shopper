package me.akulakovsky.shopper.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.List;

import me.akulakovsky.shopper.R;
import me.akulakovsky.shopper.entities.ProductPhoto;

/**
 * Created by akulakovsky on 9/12/17.
 */

public class ProductImagesAdapter extends PagerAdapter {

    private LayoutInflater inflater;
    private Context context;
    private List<ProductPhoto> data;

    private Transformation transformation;

    public ProductImagesAdapter(Context context, List<ProductPhoto> data) {
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.data = data;
        this.transformation = new RoundedTransformationBuilder()
                .borderWidthDp(0)
                .cornerRadiusDp(15)
                //.scaleType(ImageView.ScaleType.CENTER_CROP)
                .oval(false)
                .build();
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LinearLayout linearLayout = (LinearLayout) inflater.inflate(R.layout.list_item_image, container, false);

        ImageView imageView = linearLayout.findViewById(R.id.image);

        Picasso.with(context)
                .load(data.get(position).getWebUrl())
                .transform(transformation)
                //.fit()
                .into(imageView);

        container.addView(linearLayout);
        return linearLayout;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}
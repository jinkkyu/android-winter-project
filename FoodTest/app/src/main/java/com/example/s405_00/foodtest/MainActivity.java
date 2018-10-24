package com.example.s405_00.foodtest;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<Food> arr = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);
        arr.add( new Food(R.drawable.jja,
                "짜장면집","짜장면"));
        arr.add( new Food(R.drawable.bbo,
                "볶음밥집","볶음밥"));
        arr.add( new Food(R.drawable.don,
                "돈까스집","돈까스"));
        arr.add( new Food(R.drawable.pizza,
                "피자집","피자"));
    }
    public void onClick( View v){
        FoodAdapter adapter =
                new FoodAdapter(this, R.layout.food, arr);
        listView.setAdapter(adapter);
    }
}
class FoodAdapter extends ArrayAdapter<Food>{
    Context ctx;
    int resource;
    ArrayList<Food> arr;

    public FoodAdapter(@NonNull Context context,
                       int resource,
                       @NonNull ArrayList<Food> objects) {
        super(context, resource, objects);
        ctx = context;
        this.resource = resource;
        arr = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View lay = convertView;
        if( lay == null)
            lay = View.inflate(ctx, R.layout.food, null);
        ImageView imgFood = lay.findViewById(R.id.imageView);
        TextView txtSangHo = lay.findViewById(R.id.textViewSangHo);
        TextView txtFood = lay.findViewById(R.id.textViewFood);
        Food food = arr.get(position);
        imgFood.setImageResource(food.nImg);
        txtSangHo.setText(food.strSangHo);
        txtFood.setText(food.strFood);
        return lay;
    }
}
class Food
{
    public int nImg;
    public String strSangHo;
    public String strFood;
    public Food( int nImg, String strSangHo, String strFood)
    {
        this.nImg = nImg;
        this.strSangHo = strSangHo;
        this.strFood = strFood;
    }
}







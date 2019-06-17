package filivan.caloriecounter.Adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import filivan.caloriecounter.Model.Calorie;
import filivan.caloriecounter.R;

class SearchViewHolder extends RecyclerView.ViewHolder {

    public TextView mName;
    public TextView mProteins;
    public TextView mFats;
    public TextView mCarbohydrates;
    public TextView mKcal;

    public SearchViewHolder(@NonNull View itemView) {
        super(itemView);

        mName = (TextView) itemView.findViewById(R.id.name);
        mProteins = (TextView) itemView.findViewById(R.id.proteins);
        mFats = (TextView) itemView.findViewById(R.id.fats);
        mCarbohydrates = (TextView) itemView.findViewById(R.id.carbohydrates);
        mKcal = (TextView) itemView.findViewById(R.id.kcal);
    }
}



public class SearchAdapter extends RecyclerView.Adapter<SearchViewHolder> {

    private Context mContext;
    private List<Calorie> mCalories;

    public SearchAdapter(Context context, List<Calorie> calories) {
        this.mContext = context;
        this.mCalories = calories;
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.layout_item, viewGroup, false);
        return new SearchViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder searchViewHolder, int i) {
        searchViewHolder.mName.setText(mCalories.get(i).getName());
        searchViewHolder.mProteins.setText(mCalories.get(i).getProteins());
        searchViewHolder.mFats.setText(mCalories.get(i).getFats());
        searchViewHolder.mCarbohydrates.setText(mCalories.get(i).getCarbohydrates());
        searchViewHolder.mKcal.setText(mCalories.get(i).getKcal());
    }

    @Override
    public int getItemCount() {
        return mCalories.size();
    }
}

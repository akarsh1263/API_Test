package application.example.api_test;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder> {
    String[] data;
    public MoviesAdapter(String[] list){
        data=list;
    }
    @NonNull
    @Override
    public MoviesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.mlist,parent,false);

        return new MoviesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesViewHolder holder, int position) {
        String title=data[position];
        holder.textTitle.setText(title);
    }

    @Override
    public int getItemCount() {
        return data.length;
    }
    public class MoviesViewHolder extends RecyclerView.ViewHolder{
        TextView textTitle;
        public MoviesViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitle=(TextView) itemView.findViewById(R.id.textTitle);
        }
    }
}

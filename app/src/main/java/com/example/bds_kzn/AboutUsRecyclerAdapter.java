package com.example.bds_kzn;

import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
//import com.bumptech.glide.Glide;


public class AboutUsRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private static final String TAG = "AboutPageRecyclerAdapter";

        private static final int STATEMENT_REC=1;

        private static final int MEMBER_REC=0;

        private int typeRecycler;

        private List<Statement> Statements = new ArrayList<>();

        public AboutUsRecyclerAdapter(List<Statement> _statements) {
                Statements = _statements;

        }



        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                if(typeRecycler== STATEMENT_REC){
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.statement_recycler_card, parent, false);
                        return new StatementItemHolder(view);
                }
                if(typeRecycler== MEMBER_REC){
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.member_recycler_card, parent, false);
                        return new MemberItemHolder(view);
                }

                return null;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
                if (typeRecycler == STATEMENT_REC) {
                        Statement statement = Statements.get(position);

                        StatementItemHolder statementHolder = (StatementItemHolder) holder; // Cast to StatementItemHolder

                        // Set the title and label of the statement.
                        statementHolder.statementTitle.setText(statement.getTitle());
                        statementHolder.statementLabel.setText(statement.getLabel());

                        // Load the image into the ImageView.
                        Glide.with(statementHolder.statementBadge.getContext())
                                .load(statement.getImage())
                                .centerCrop()
                                .into(statementHolder.statementBadge);
                } else if (typeRecycler == MEMBER_REC) {
                        MemberItemHolder memberHolder = (MemberItemHolder) holder; // Cast to MemberItemHolder
                        Statement statement = Statements.get(position);
                        // Set the title and label of the statement.
                        memberHolder.memberName.setText(statement.getTitle());
                        memberHolder.memberDescription.setText(statement.getLabel());
                        // Load the image into the ImageView.
                        Glide.with(memberHolder.memberImage.getContext())
                                .load(statement.getImage())
                                .centerCrop()
                                .into(memberHolder.memberImage);
                }

                if (position == Statements.size() - 1) {

                        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) holder.itemView.getLayoutParams();
                        params.rightMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 15, holder.itemView.getResources().getDisplayMetrics());
                        holder.itemView.setLayoutParams(params);
                }
        }

        public void RecyclerType(int _typeRecycler){typeRecycler = _typeRecycler;}


        @Override
        public int getItemCount() {
                return Statements.size();
        }




        public class StatementItemHolder extends RecyclerView.ViewHolder {

                private ImageView statementBadge;
                private TextView statementTitle;
                private TextView statementLabel;


                public StatementItemHolder(@NonNull View itemView) {
                        super(itemView);
                        statementBadge = itemView.findViewById(R.id.statement_badge);
                        statementTitle = itemView.findViewById(R.id.statement_title);
                        statementLabel = itemView.findViewById(R.id.statement_label);
                }
        }

        public class MemberItemHolder extends RecyclerView.ViewHolder {

                private TextView memberName;
                private TextView memberDescription;
                private ImageView memberImage;

                public MemberItemHolder(@NonNull View itemView) {
                        super(itemView);
                        memberImage = itemView.findViewById(R.id.member_image);
                        memberName = itemView.findViewById(R.id.member_name);
                        memberDescription = itemView.findViewById(R.id.member_description);
                }
        }
}



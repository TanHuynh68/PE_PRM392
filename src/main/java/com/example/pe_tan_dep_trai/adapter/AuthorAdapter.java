package com.example.pe_tan_dep_trai.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.example.pe_tan_dep_trai.R;
import com.example.pe_tan_dep_trai.model.Author;

import java.util.List;

public class AuthorAdapter extends BaseAdapter {
    private Context context;
    private List<Author> authorList;

    public AuthorAdapter(Context context, List<Author> authorList) {
        this.context = context;
        this.authorList = authorList;
    }

    @Override
    public int getCount() {
        return authorList.size();
    }

    @Override
    public Object getItem(int position) {
        return authorList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    static class AuthorViewHolder {
        TextView txtAuthorId, txtAuthorName, txtAuthorEmail, txtAuthorPhone, txtAuthorAddress;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        AuthorViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_author, parent, false);
            holder = new AuthorViewHolder();
            holder.txtAuthorId = convertView.findViewById(R.id.txtAuthorId);
            holder.txtAuthorName = convertView.findViewById(R.id.txtAuthorName);
            holder.txtAuthorEmail = convertView.findViewById(R.id.txtAuthorEmail);
            holder.txtAuthorPhone = convertView.findViewById(R.id.txtAuthorPhone);
            holder.txtAuthorAddress = convertView.findViewById(R.id.txtAuthorAddress);
            convertView.setTag(holder);
        } else {
            holder = (AuthorViewHolder) convertView.getTag();
        }

        Author author = authorList.get(position);
        holder.txtAuthorId.setText("Author ID: " + author.getAuthorId());
        holder.txtAuthorName.setText("Name: " + author.getName());
        holder.txtAuthorEmail.setText("Email: " + author.getEmail());
        holder.txtAuthorPhone.setText("Phone: " + author.getPhone());
        holder.txtAuthorAddress.setText("Address: " + author.getAddress());

        return convertView;
    }
}


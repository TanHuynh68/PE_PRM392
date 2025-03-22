package com.example.pe_tan_dep_trai.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.pe_tan_dep_trai.R;
import com.example.pe_tan_dep_trai.model.Book;

import java.util.List;

public class BookAdapter extends BaseAdapter {
    private Context context;
    private List<Book> bookList;

    public BookAdapter(Context context, List<Book> bookList) {
        this.context = context;
        this.bookList = bookList;
    }

    @Override
    public int getCount() {
        return bookList.size();
    }

    @Override
    public Object getItem(int position) {
        return bookList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    static class ViewHolder {
        TextView txtBookTitle, txtBookId, txtAuthorId, txtPublicDate, txtBookType;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_book, parent, false);
            holder = new ViewHolder();
            holder.txtBookTitle = convertView.findViewById(R.id.txtBookTitle);
            holder.txtBookId = convertView.findViewById(R.id.txtBookId);
            holder.txtAuthorId = convertView.findViewById(R.id.txtAuthorId);
            holder.txtPublicDate = convertView.findViewById(R.id.txtPublicDate);
            holder.txtBookType = convertView.findViewById(R.id.txtBookType);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Book book = bookList.get(position);
        holder.txtBookTitle.setText("Title: " + book.getBookTitle());
        holder.txtBookId.setText("Book ID: " + book.getBookId());
        holder.txtAuthorId.setText("Author ID: " + book.getAuthorId());
        holder.txtPublicDate.setText("Published: " + book.getPublicationDate());
        holder.txtBookType.setText("Type: " + book.getType());

        return convertView;
    }
}

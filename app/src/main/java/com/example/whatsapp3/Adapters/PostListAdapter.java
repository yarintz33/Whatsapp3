package com.example.whatsapp3.Adapters;

public class PostListAdapter{

    /*extends RecyclerView.Adapter<PostListAdapter.PostViewHolder> {

    class PostViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView id;

        private PostViewHolder(View itemView){
            super(itemView);
            name = itemView.findViewById(R.id.contactName);
            id = itemView.findViewById(R.id.contactId);
        }
    }

    private final LayoutInflater mInflater;
    private List<Post> posts;

    public PostListAdapter(Context context){ mInflater = LayoutInflater.from(context);}

    @Override
    public PostListAdapter.PostViewHolder onCreateViewHolder (ViewGroup parent, int ViewType){
        View itemView = mInflater.inflate(R.layout.Post_item_layout, parent,false);
        return  new PostListAdapter.PostViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PostListAdapter.PostViewHolder holder, int position) {
        if (posts != null){
            final PostContact current = posts.get(position);
            holder.name.setText(current.getName());
        }
    }

    @Override
    public int getItemCount() {
        if(posts!= null){
            return  posts.size();
        }
        return 0;
    }

    public void setPosts(List<Post> posts){
        this.posts = posts;
        notifyDataSetChanged();
    }
    public List<Post> getPosts() {return posts;}*/

}

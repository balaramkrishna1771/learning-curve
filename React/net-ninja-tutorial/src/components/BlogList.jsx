import { Link } from "react-router-dom";

const BlogList = ({ blogs }) => {

    return (
        <div className="flex flex-col justify-center items-center">
            {blogs.map((eachBlog) => (
                <div className="bg-slate-400" key={eachBlog.id}>
                    <Link to={`/blogs/${eachBlog.id}`}>
                        <h2>{eachBlog.title}</h2>
                        <p>Writtern by {eachBlog.author}</p>
                    </Link>
                </div>
            ))}
        </div>
    );
}

export default BlogList;
import { useNavigate, useParams } from "react-router-dom";
import useFetch from "./useFetch";

const BlogPreview = () => {
    const { id } = useParams();

    const { data: blog, isLoading, error } = useFetch('http://localhost:8000/blogs/' + id);

    const navigate = useNavigate();

    function handleClick() {
        fetch('http://localhost:8000/blogs/' + blog.id, {
            method: 'DELETE'
        })
            .then((res) => {
                console.log(res);
                if (res.ok) {
                    navigate('/')
                }
            });
    }

    return (
        <div>
            {blog && (
                <article>
                    <h1>{blog.title}</h1>
                    <p>Written by {blog.author}</p>
                    <p>{blog.body}</p>
                    <button onClick={handleClick}>Delete Blog</button>
                </article>
            )}
        </div>
    );
}

export default BlogPreview;
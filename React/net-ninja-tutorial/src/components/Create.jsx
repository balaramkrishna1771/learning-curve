import { useState } from "react";
import { useNavigate } from "react-router-dom";


export default function Create() {
    const [title, setTitle] = useState('');
    const [body, setBody] = useState('');
    const [author, setAuthor] = useState('');
    const [isCreating, setIsCreating] = useState(false);

    const navigate = useNavigate();

    function handleSubmit(e) {
        e.preventDefault();
        const blog = { title, body, author };

        setIsCreating(true);

        fetch('http://localhost:8000/blogs', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(blog)
        }).then((res) => {
            if (res.ok) {
                navigate('/');
                setIsCreating(false);
            }
        })

    }
    return (
        <div className=" bg-blue-300 flex flex-col justify-center items-center">
            <form onSubmit={handleSubmit} className="flex flex-col justify-center">
                <label>Title</label>
                <input
                    type="text"
                    value={title}
                    onChange={(e) => setTitle(e.target.value)}
                    required
                />
                <label>Body</label>
                <textarea
                    value={body}
                    onChange={(e) => setBody(e.target.value)}
                    required
                />
                <label>Author</label>
                <select
                    value={author}
                    onChange={(e) => setAuthor(e.target.value)}
                    required
                >
                    <option defaultValue=''>Select Author</option>
                    <option value="yoshi">yoshi</option>
                    <option value="mario">mario</option>
                    <option value="luigi">luigi</option>
                </select>
                {!isCreating && <button type="submit">Add Blog</button>}
                {isCreating && <button disabled>Adding Blog..</button>}
            </form>
        </div>
    );
}
export default function SelectedProject({ project, onDeleteProject }) {
    const formattedDate = new Date(project.dueDate).toLocaleDateString('en-US', {
        year: 'numeric',
        month: 'short',
        day: 'numeric'
    })
    return (
        <div>
            <header>
                <div>
                    <h1>{project.title}</h1>
                    <button onClick={onDeleteProject}>Delete</button>
                </div>
                <p>{formattedDate}</p>
                <p className="whitespace-pre-wrap">{project.description}</p>
            </header>
        </div>
    );
}
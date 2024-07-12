import { useState } from "react";

export default function SideBar({ onStartAddProject, projects, onSelectProject, selectedProjectID }) {

    return (
        <aside className=" w-1/5 px-6 py-4 bg-stone-900 text-stone-50 rounded-xl ">
            <h2 className="font-bold">Your Projects</h2>
            <div >
                <button
                    onClick={onStartAddProject}
                    className="bg-gray-400 rounded-lg px-3 py-1 text-sm hover:bg-slate-100 hover:text-stone-800">+ Add Project</button>
            </div>
            <ul className="mt-8">
                {projects.map((project) => {
                    let cssClass = "w-full text-stone-50 hover:bg-stone-400"
                    if (project.id === selectedProjectID) {
                        cssClass += ' bg-stone-40';
                    }
                    else {
                        cssClass += ' text-stone-100';
                    }
                    return (<li key={project.ID} >
                        <button onClick={() => onSelectProject(project.ID)} className={cssClass}>
                            {project.title}
                        </button>
                    </li>)
                })}
            </ul>
            {console.log(projects)}
        </aside>

    );
}
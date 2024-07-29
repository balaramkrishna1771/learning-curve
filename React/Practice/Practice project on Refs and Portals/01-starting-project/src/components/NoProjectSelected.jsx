import noProjectImage from '../assets/no-projects.png';

export default function NoProjectSelected({ onStartAddProject }) {
    return <div>
        <img src={noProjectImage} alt='no project selected' className='w-16 h-16 object-contain mx-auto' />
        <h2 className='text-xl font-bold text-stone-500'>No Project Selected</h2>
        <p>Select a project or get started with a new one</p>
        <p>
            <button onClick={onStartAddProject} className='bg-stone-600 text-stone-100'>Create new project</button>
        </p>
    </div>
}
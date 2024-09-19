import { useState } from 'react';
import LoginPage from './LoginPage';

export default function HomePageSurvey() {
    const [selectedStage, setSelectedStage] = useState('');
    function handleSubmit(event) {
        setSelectedStage(event.target.value);
    }
    return (
        <div className="flex items-center justify-center h-full">
            <form className="bg-white p-6 rounded-lg shadow-lg w-90">
                <h2 className="text-2xl font-bold mb-4">Select Stage</h2>
                <div className="mb-4">
                    <select id="stages" className="bg-white p-4 rounded-lg shadow-lg w-90" onChange={handleSubmit}>
                        <option value="">Select an option</option>
                        <option value="high school">Considering medicine, in high school</option>
                        <option value="undergrad">In undergrad, applying to med school</option>
                        <option value="medical student">Medical Student</option>
                    </select>
                </div>

                <button
                    type="submit"
                    className="w-full bg-stone-600 hover:bg-stone-300 hover:text-stone-600 text-stone-300 hover:pointer font-bold py-2 px-4 rounded"
                >
                    Next
                </button>

            </form>

        </div>

    );
}

import { useState } from "react";
import NavBar from "./NavBar.jsx";
import homeImage from "../resources/media/102676.webp";
import LoginPage from "./LoginPage.jsx";

export default function Home() {

    const [selectedStage, setSelectedStage] = useState('');

    const proceedLogin = false;

    function handleSubmit() {
        alert(selectedStage);
        selectedStage === true ? proceedLogin = true : proceedLogin = false;
    }
    return (
        <>
            {/* <LoginPage /> */}

            <div
                className="relative bg-fixed bg-cover bg-center h-screen"
                style={{
                    backgroundImage: `url(${homeImage})`,
                }}
            >
                <NavBar />
                <div className="flex items-center justify-center h-full">
                    <form className="bg-white p-6 rounded-lg shadow-lg w-96" onSubmit={handleSubmit}>
                        <h2 className="text-2xl font-bold mb-4">Select Medical Education Stage</h2>

                        <div className="mb-4">
                            <label className="block mb-2 text-lg font-medium">
                                <input
                                    type="radio"
                                    value="Undergraduate"
                                    checked={selectedStage === 'Undergraduate'}
                                    onChange={(e) => setSelectedStage(e.target.value)}
                                    className="mr-2"
                                />
                                Undergraduate
                            </label>
                            <label className="block mb-2 text-lg font-medium">
                                <input
                                    type="radio"
                                    value="Postgraduate"
                                    checked={selectedStage === 'Postgraduate'}
                                    onChange={(e) => setSelectedStage(e.target.value)}
                                    className="mr-2"
                                />
                                Postgraduate
                            </label>
                            <label className="block mb-2 text-lg font-medium">
                                <input
                                    type="radio"
                                    value="Residency"
                                    checked={selectedStage === 'Residency'}
                                    onChange={(e) => setSelectedStage(e.target.value)}
                                    className="mr-2"
                                />
                                Residency
                            </label>
                        </div>

                        <button
                            type="submit"
                            className="w-full bg-stone-600 hover:bg-stone-300 hover:text-stone-600 text-stone-300 hover:pointer font-bold py-2 px-4 rounded"
                        >
                            Next
                        </button>
                    </form>
                </div>
            </div>
        </>

    );
}
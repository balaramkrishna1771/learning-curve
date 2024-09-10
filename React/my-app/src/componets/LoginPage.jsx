import { useState } from "react";
// import LoginFormInputField from "./LoginFormInputField.jsx";

export default function LoginPage() {
    const [userInput, setUserInput] = useState({
        user: '',
        password: ''
    });

    return (


        <div className="flex items-center justify-center h-full">
            <form className="bg-white p-6 rounded-lg shadow-lg w-96">
                <h2 className="text-2xl font-bold mb-4">Select Medical Education Stage</h2>

                <div className="mb-4">
                    <label className="block mb-2 text-lg font-medium">
                        <input
                            type="text"
                            placeholder="Enter your username"
                            className="mr-2"
                        />
                        Username
                    </label>
                    <label className="block mb-2 text-lg font-medium">
                        <input
                            type="password"
                            placeholder="Enter your password"
                            className="mr-2"
                        />
                        Password
                    </label>
                </div>

                <button
                    type="submit"
                    className="w-full bg-stone-600 hover:bg-stone-300 hover:text-stone-600 text-stone-300 hover:pointer font-bold py-2 px-4 rounded"
                >
                    Login
                </button>
            </form>
        </div>
    );
}
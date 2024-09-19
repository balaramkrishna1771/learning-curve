import { useState } from "react";
import axios from "axios";
import NavBar from "./NavBar.jsx";
import SignUp from "./SignUp.jsx";
// import LoginFormInputField from "./LoginFormInputField.jsx";

export default function LoginPage({onSignUpClick}) {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');
    const [success, setSuccess] = useState('');

    function handleSubmit(e){
        e.preventDefault();

        async function submitLogin(){
            try{
                const response = await axios.post("http://localhost:8080/v1/login",{username,password});
                if(response.data === ''){
                    alert("Invalid Username");
                }
                else{
                    if(response.status === 200){
                        setSuccess(response.data);
                        setError('');
                    }else{
                        setError(response.data);
                        setSuccess('');
                    }
                }

            }catch (err)
            {
                setError('Error Occured while logging in'+err);
                setSuccess('');
            }
        }
        submitLogin();
    }

    return (
        <>
            <div className="flex items-center justify-center h-full relative">
                <form onSubmit={handleSubmit} className="bg-white p-6 rounded-lg shadow-lg w-96">
                    <h2 className="text-2xl font-bold mb-4">Login</h2>

                    <div className="mb-4">
                        <label className="block mb-2 text-lg font-medium" htmlFor="email">
                            Username
                        </label>
                        <input
                            type="text"
                            id="email"
                            onChange={(e) => setUsername(e.target.value)}
                            placeholder="Enter your username"
                            required
                            className="w-full px-3 py-2 border border-gray-300 rounded"
                        />
                    </div>

                    <div className="mb-6">
                        <label className="block mb-2 text-lg font-medium" htmlFor="password">
                            Password
                        </label>
                        <input
                            type="password"
                            id="password"
                            onChange={(e) => setPassword(e.target.value)}
                            required
                            placeholder="Enter your password"
                            className="w-full px-3 py-2 border border-gray-300 rounded"
                        />
                    </div>
                    {error && <p className="block mb-2 text-lg font-medium text-red-600">error</p>}
                    {success === "Authenticated" ?
                        <p className="block mb-2 text-md font-medium text-green-600">{success}</p> :
                        <p className="block mb-2 text-md font-medium text-red-600">{success}</p>}
                    <button
                        type="submit"
                        className="w-full bg-stone-600 hover:bg-stone-300 hover:text-stone-600 text-stone-300 font-bold py-2 px-4 rounded transition duration-300"
                    >
                        Login
                    </button>
                    <p className="block mb-2 text-md font-medium">Create account? <button onClick={onSignUpClick}>Sign
                        Up</button></p>
                </form>

            </div>

        </>
    );
}
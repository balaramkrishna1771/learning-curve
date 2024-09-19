import LoginPage from "./LoginPage";
import {useState} from "react";
import axios from "axios";

export default function SignUp({onLoginClick}){


    const[userInput, setUserInput] = useState({
        username :'',
        password : '',
        firstname : '',
        lastname : ''

    })

    const [error, setError] = useState('');
    const [success, setSuccess] = useState('');

    function handleInputChange(e){
        const {name, value} = e.target;
        setUserInput({
            ...userInput,
            [name] : value
        });
        // alert(userInput);
    }

    function handleCreateAccount(e){
        e.preventDefault();

        async function submitSignup(){
            try{
                // alert(userInput.firstname + userInput.id + userInput.username);
            const response = await axios.post("http://localhost:8080/v1/signup",userInput);
            // alert(response.data);
                if(response.status === 200){
                    alert("Successful");
                    setSuccess(response.data);
                    setError('');
                }else{
                    setError(response.data);
                    setSuccess('');
                }

            }catch (err)
            {
                setError('Error Occured while logging in'+err);
                setSuccess('');
            }
        }
       submitSignup();
    }

    return(<>
        <div className="flex items-center justify-center h-full relative">
            <form className="bg-white p-6 rounded-lg shadow-lg w-96" onSubmit={handleCreateAccount}>
                <h2 className="text-2xl font-bold mb-4">Sign Up</h2>

                <div className="mb-4">
                    <label className="block mb-2 text-lg font-medium" htmlFor="email">
                        Email
                    </label>
                    <input
                        type="text"
                        id="username"
                        name="username"
                        placeholder="Enter your email"
                        value={userInput.username}
                        onChange={handleInputChange}
                        required
                        className="w-full px-3 py-2 border border-gray-300 rounded"
                    />
                </div>
                <div className="mb-4">
                    <label className="block mb-2 text-lg font-medium" htmlFor="firstname">
                        First Name
                    </label>
                    <input
                        type="text"
                        id="firstname"
                        name="firstname"
                        placeholder="Enter your first name"
                        value={userInput.firstname}
                        onChange={handleInputChange}
                        required
                        className="w-full px-3 py-2 border border-gray-300 rounded"
                    />
                </div>
                <div className="mb-4">
                    <label className="block mb-2 text-lg font-medium" htmlFor="lastname">
                        Last Name
                    </label>
                    <input
                        type="text"
                        id="lastname"
                        name="lastname"
                        placeholder="Enter your last name"
                        value={userInput.lastname}
                        onChange={handleInputChange}
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
                        name="password"
                        value={userInput.password}
                        onChange={handleInputChange}
                        required
                        placeholder="Enter your password"
                        className="w-full px-3 py-2 border border-gray-300 rounded"
                    />
                </div>
                <button
                    type="submit"
                    className="w-full bg-stone-600 hover:bg-stone-300 hover:text-stone-600 text-stone-300 font-bold py-2 px-4 rounded transition duration-300"
                >
                    Create account
                </button>
                <p className="block mb-2 text-md font-medium">Already have an account? <button onClick={onLoginClick}>Login</button></p>
            </form>

        </div>
    </>);
}
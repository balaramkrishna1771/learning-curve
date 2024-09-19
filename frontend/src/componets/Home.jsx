import { useState } from "react";
import {BrowserRouter as Router, Route, Routes, Navigate    } from "react-router-dom";
import NavBar from "./NavBar.jsx";
import homeImage from "../resources/media/102676.webp";
import LoginPage from "./LoginPage.jsx";
import HomePageSurvey from "./HomePageSurvey.jsx";
import SignUp from "./SignUp";


export default function Home() {
    const[showLogin, setShowLogin] = useState({
        activateLogin : false,
        activateSignUp : false,
        activateSurvey : true
    });

    function handleLoginClick(){
        setShowLogin({
            activateSignUp : false,
            activateLogin : true,
            activateSurvey : false
        });
    }
    function handleSignUpClick(){
        setShowLogin({
            ...showLogin,
            activateSignUp : true,
            activateLogin : false,
            activateSurvey : false

        });
    }


    return (
        <>

            <div
                className="relative bg-fixed bg-cover bg-center h-screen"
                style={{
                    backgroundImage: `url(${homeImage})`,
                }}
            >
                <NavBar onLoginClick = {handleLoginClick} isLoginActive = {showLogin.activateLogin}/>
                {showLogin.activateSurvey  ? <HomePageSurvey/> : showLogin.activateLogin ? (<div className="flex items-center justify-center h-full">
                        <LoginPage onSignUpClick = {handleSignUpClick}/>
                    </div>) : (<div className="flex items-center justify-center h-full">
                        <SignUp onLoginClick = {handleLoginClick}/>
                    </div>)}


            </div>


        </>

    );
}
import LoginPage from "./LoginPage.jsx";
import {useState} from "react";

export default function NavBar({onLoginClick, isLoginActive}) {
    const buttonClassName = `m-0 p-4 justify-center hover:bg-stone-600 hover:text-stone-50 hover:pointer hover:border-1`;

    return (
        <div className="fixed bg-slate-50 flex justify-between items-center w-full shadow-lg">
            <div className="text-left px-4 hover:pointer"><p>MediBuddy</p ></div>
            <div className="flex justify-end">

                <div className={buttonClassName}>
                    <button onClick={onLoginClick}>Dashboard</button>
                </div>
                <div className={buttonClassName}>
                    <button>Resources</button>
                </div>
                <div className={buttonClassName}>
                    <button>Calender</button>
                </div>
                <div className={buttonClassName}>
                    <button>Careers</button>
                </div>
                <div className={buttonClassName}>
                    <button>Contact</button>
                </div>
                <div className="m-0 p-4 justify-center bg-stone-600 hover:bg-stone-300 hover:text-stone-600 text-stone-300">
                    <button onClick={onLoginClick}>{isLoginActive ? <a href="/Home.jsx">Home</a> : "Login" }</button>
                </div>
            </div>
        </div>
    );
}
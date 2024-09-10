import { useState } from "react";
import '../styles/SideMenu.css';

export default function SideMenu() {
    const [isActive, setIsActive] = useState(false);
    const sideMenu = (
        <div className={`fixed top-0 left-0 w-64 h-full bg-gray-800 text-white transition-transform transform ${isActive ? 'translate-x-0' : '-translate-x-full'
            } md:translate-x-0 md:relative md:w-64`}>
            <ul>
                <li><a href="#">Dashboard</a></li>
                <li><a href="#">Resources</a></li>
                <li><a href="#">Calender</a></li>
                <li><a href="#">Contact</a></li>
            </ul>
        </div >);

    function handleClickEvent() {
        isActive === true ? setIsActive(false) : setIsActive(true);
    }

    return (
        <div>
            <button onClick={handleClickEvent} className="bg-stone-600">Burger Icon</button>

            {isActive === true && sideMenu}
        </div>
    );
}
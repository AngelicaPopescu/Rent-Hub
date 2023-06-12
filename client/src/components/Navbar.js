import React from "react";
import './Navbar.css';
import logoText from "../images/logos/logoText.png"
import logoImage from "../images/logos/logoImage.png"
import AccountCircleIcon from '@mui/icons-material/AccountCircle';
import PeopleIcon from '@mui/icons-material/People';



const userId = sessionStorage.getItem("clientId");
console.log(userId)

export default function Navbar() {
    return (
        <nav className="nav flex flex-wrap items-center justify-between px-4">
            <div className="flex flex-no-shrink items-center mr-6 py-3 text-grey-darkest">

                <a href={"/properties"}>
                    <img height={40} width={100} src={logoImage} alt={"logo"}/>
                </a>
                <a href={"/properties"}>
                    <img height={50} width={150} src={logoText} alt={"logo"}/>
                </a>
            </div>

            <input className="menu-btn hidden" type="checkbox" id="menu-btn"/>
            <label className="menu-icon block cursor-pointer md:hidden px-2 py-4 relative select-none"
                   htmlFor="menu-btn">
                <span className="navicon bg-grey-darkest flex items-center relative"></span>
            </label>

            <ul className="menu border-b md:border-none flex justify-end list-reset m-0 w-full md:w-auto">
                <li className="border-t md:border-none">
                    <a href="#"
                       className="block md:inline-block px-4 py-3 no-underline text-grey-darkest hover:text-grey-darker font-bold">Advertise
                        your property</a>
                </li>

                <li className="border-t md:border-none">

                    <a href="#"
                       className="block md:inline-block px-4 py-3 no-underline text-grey-darkest hover:text-grey-darker">
                    </a>
                </li>

                <li className="border-t md:border-none">
                    <a href="/api/clients/logout"
                       className="block md:inline-block px-4 py-3 no-underline text-grey-darkest hover:text-grey-darker"><i
                        className="material-icons">logout</i></a>
                </li>
                <li className="border-t md:border-none">
                    <a href="http://localhost:3000/api/clients/"
                       className="block md:inline-block px-4 py-3 no-underline text-grey-darkest hover:text-grey-darker"><i
                        className="material-icons"><PeopleIcon/></i></a>
                </li>
                <li className="border-t md:border-none">
                <a href={userId ? `http://localhost:3000/api/clients/${userId}` : "http://localhost:3000/api/clients/register"}
                    className="block md:inline-block px-4 py-3 no-underline text-grey-darkest hover:text-grey-darker">
                    <i className="material-icons"><AccountCircleIcon/></i>
                </a>
                </li>

            </ul>
        </nav>

    );
}

import { CORE_CONCEPTS } from "../data.js";
import CoreComponent from "./CoreComponent.jsx";

export default function CoreConcepts(){
    return(
        <section id="core-concepts">
        <h2>Core Concepts</h2>
        <ul>
        {CORE_CONCEPTS.map((conceptItem) => (<CoreComponent key={conceptItem.title} {...conceptItem}/>))}
        </ul>
        </section>
    );
}
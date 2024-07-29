import TabConcepts from "./TabConcepts";
import { useState } from "react";
import { EXAMPLES } from "../data";
import Section from "./Section";
import Tab from "./Tabs";

export default function Examples() {
    const [selectedTopic, setSelectedTopic] = useState();
    function handleClick(selectedButton) {
        setSelectedTopic(selectedButton);

    }

    let tabcontent = <p>Please slect the topic</p>;

    if (selectedTopic) {
        tabcontent = (
            <div id="tab-content">
                <h3>{EXAMPLES[selectedTopic].title}</h3>
                <p>{EXAMPLES[selectedTopic].description}</p>
                <pre>
                    <code>
                        {EXAMPLES[selectedTopic].code}
                    </code>
                </pre>
            </div>
        );
    }
    return (
        <Section id="examples">
            <h2>Examples</h2>
            <Tab
                MenuContainer="menu"
                menuButtons={
                    <>
                        <TabConcepts isSelected={selectedTopic === 'components'} onClick={() => handleClick("components")}>Component</TabConcepts>
                        <TabConcepts isSelected={selectedTopic === 'jsx'} onClick={() => handleClick("jsx")}>JSX</TabConcepts>
                        <TabConcepts isSelected={selectedTopic === 'props'} onClick={() => handleClick("props")}>Props</TabConcepts>
                        <TabConcepts isSelected={selectedTopic === 'state'} onClick={() => handleClick("state")}>State</TabConcepts>
                    </>
                } >{tabcontent}
            </Tab>
        </Section>
    );
}
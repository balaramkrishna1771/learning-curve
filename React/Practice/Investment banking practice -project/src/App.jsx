import { useState } from "react";
import Input from "./components/Input.jsx";
import Header from "./components/Header.jsx";
import Table from "./components/Table.jsx";

function App() {
  const [inpValues, setInputValues] = useState({
    initialInvestment: 10000,
    annualInvestment: 1200,
    expectedReturn: 6,
    duration: 10
  });

  const isValidInput = inpValues.duration >= 1;
  function handleChange(inpIdentifier, newValue) {
    setInputValues({
      ...inpValues,
      [inpIdentifier]: +newValue
    });
  }
  return (

    <>
      <Header id="header" />
      <Input inpValues={inpValues} handleChange={handleChange} />
      {isValidInput ? <Table inpValues={inpValues} /> : <p className="center">Please Enter a valid duration</p>}
    </>
  )
}

export default App

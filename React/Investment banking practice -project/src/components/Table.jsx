import { calculateInvestmentResults, formatter } from "../util/investment.js";

export default function Table({ inpValues }) {
    const result = calculateInvestmentResults(inpValues);
    const initialInvestment = (result[0].valueEndOfYear -
        result[0].interest -
        result[0].annualInvestment);
    console.log(result);
    return (
        <table id="result">
            <thead>
                <tr>
                    <th>Year</th>
                    <th>Investment Value</th>
                    <th>Interest (Year)</th>
                    <th>Total Interest</th>
                    <th>Invested Capital</th>
                </tr>
            </thead>
            <tbody>
                {result.map((eachYear) => {
                    const totalInterest = eachYear.valueEndOfYear - eachYear.annualInvestment * eachYear.year - initialInvestment;
                    const totalAmountInvested = eachYear.valueEndOfYear - totalInterest;
                    return (
                        <tr key={eachYear.year}>
                            <td>{eachYear.year}</td>
                            <td>{formatter.format(eachYear.valueEndOfYear)}</td>
                            <td>{formatter.format(eachYear.interest)}</td>
                            <td>{formatter.format(totalInterest)}</td>
                            <td>{formatter.format(totalAmountInvested)}</td>
                        </tr>
                    );
                })}
            </tbody>
        </table>
    );
}
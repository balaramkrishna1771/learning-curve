
export default function Input({ handleChange, inpValues }) {
    return (
        <section id="user-input">
            <div className="input-group">
                <p>
                    <lable>INITIAL INVESTMENT</lable>
                    <input type="number" required
                        value={inpValues.initialInvestment}
                        onChange={(event) => { handleChange("initialInvestment", event.target.value) }} />
                </p>
                <p>
                    <lable>ANNUAL INVESTMENT</lable>
                    <input type="number" required
                        value={inpValues.annualInvestment}
                        onChange={(event) => { handleChange("annualInvestment", event.target.value) }} />
                </p>
            </div>
            <div className="input-group">
                <p>
                    <lable>EXPECTED RETURN</lable>
                    <input type="number" required
                        value={inpValues.expectedReturn}
                        onChange={(event) => { handleChange("expectedReturn", event.target.value) }} />
                </p>
                <p>
                    <lable>DURATION</lable>
                    <input type="number" required
                        value={inpValues.duration}
                        onChange={(event) => { handleChange("duration", event.target.value) }} />
                </p>
            </div>
        </section>
    );
}
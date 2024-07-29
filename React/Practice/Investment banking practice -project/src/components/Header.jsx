export default function Header({ ...props }) {
    return (
        <header {...props}>
            <img src="investment-calculator-logo.png" alt="investment app header logo" />
            <h1>React Investment Calculator</h1>
        </header>
    );
}
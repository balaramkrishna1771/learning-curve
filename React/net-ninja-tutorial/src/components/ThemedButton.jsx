import { useContext } from "react";
import { ThemeContext } from "./ThemeProvider.jsx";


function ThemedButton() {

    const { themeMode, setThemeMode } = useContext(ThemeContext);
    return (<button
        onClick={() => setThemeMode(themeMode === 'light' ? 'dark' : 'light')}
        style={{ background: themeMode === 'light' ? "gray" : "black" }} >
        Toggle Theme
    </button>);
}

export default ThemedButton;
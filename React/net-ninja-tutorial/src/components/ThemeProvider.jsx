import { useState } from "react";


import { createContext } from "react";

export const ThemeContext = createContext();

function ThemeProvider({ children }) {
    const [themeMode, setThemeMode] = useState('light');

    return (
        <ThemeContext.Provider value={{ themeMode, setThemeMode }}>
            {children}
        </ThemeContext.Provider>
    );
}

export default ThemeProvider;
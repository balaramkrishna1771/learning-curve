export default function Tab({ children, menuButtons, MenuContainer }) {
    return <>
        <MenuContainer>
            {menuButtons}
        </MenuContainer>
        {children}
    </>
}
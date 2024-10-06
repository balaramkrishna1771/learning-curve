const Navbar = () => {
    return (
        <nav className="navbar">
            <h1>Ram Blog</h1>
            <div className="flex flex-row justify-end space-x-3 bg-stone-200">
                <a href="/">Home</a>
                <a href="/create">New Blog</a>
            </div>
        </nav>
    );
}

export default Navbar;

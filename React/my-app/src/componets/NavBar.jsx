
export default function NavBar() {
    const buttonClassName = `m-0 p-4 justify-center hover:bg-stone-600 hover:text-stone-50 hover:pointer`;
    return (
        <div className="fixed bg-slate-50 flex justify-between items-center w-full shadow-lg">
            <div className="text-left px-4 hover:pointer"><p>MediBuddy</p ></div>
            <div className="flex justify-end space-x-3">

                <div className={buttonClassName}><button>Dashboard</button></div>
                <div className={buttonClassName}><button>Resources</button></div>
                <div className={buttonClassName}><button>Calender</button></div>
                <div className={buttonClassName}><button>Careers</button></div>
                <div className={buttonClassName}><button>Contact</button></div>

            </div>
        </div>
    );
}
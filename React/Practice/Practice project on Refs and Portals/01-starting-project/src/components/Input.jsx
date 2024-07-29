import { forwardRef } from "react";


const Input = forwardRef(function Input({ label, textarea, type }, ref) {
    return (
        <p className="flex flex-col gap-1 my-4">
            <label>{label}</label>
            {textarea ? <textarea ref={ref} type={type} /> : <input ref={ref} type={type} />}
        </p>
    );
});

export default Input;
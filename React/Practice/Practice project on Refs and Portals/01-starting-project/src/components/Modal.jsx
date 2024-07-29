import { forwardRef, useRef, useImperativeHandle } from "react";
import { createPortal } from "react-dom";

const Modal = forwardRef(function Modal({ children, buttonCaption }, ref) {
    const dialog = useRef();
    useImperativeHandle(ref, () => {
        return {
            open() {
                dialog.current.showModal();
            }
        };
    });
    return createPortal(<dialog ref={dialog}>
        {children}
        <form method="dialog">
            <button className="bg-stone-600 text-stone-50">{buttonCaption}</button>
        </form>
    </dialog>, document.getElementById("modal-root"));
})

export default Modal;
import { useRef } from "react";
import Input from "./Input.jsx";
import Modal from "./Modal.jsx";

export default function NewProject({ onAdd, onCancel }) {
    const title = useRef();
    const description = useRef();
    const dueDate = useRef();
    const modal = useRef();

    function handleSave() {
        const enteredTitle = title.current.value;
        const enteredDescription = description.current.value;
        const enteredDueDate = dueDate.current.value;

        if (enteredTitle.trim() === '' ||
            enteredDescription.trim() === '' ||
            enteredDueDate.trim() === '') {
            //
            modal.current.open();
            return;

        }

        onAdd({
            title: enteredTitle,
            description: enteredDescription,
            dueDate: enteredDueDate
        });
    }

    return (
        <>
            <Modal ref={modal} buttonCaption="Okay">
                <h2>Invalid Input</h2>

            </Modal>
            <div className="w-[35-rem]">
                <menu className="flex items-center justify-end gap-4 my-4">
                    <button onClick={onCancel}>Cancel</button>
                    <button onClick={handleSave} className="bg-stone-800 text-stone-100 hover:bg-stone-400 hover:text-slate-800 rounded-lg px-2 py-2">Save</button>
                </menu>
                <div>
                    <Input type="text" ref={title} label={"Title"} />
                    <Input ref={description} label={"Description"} textarea />
                    <Input type="date" ref={dueDate} label={"Due date"} />
                </div>
            </div>
        </>
    );
}
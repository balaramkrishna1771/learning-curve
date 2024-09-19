export default function LoginFormInputField({ lable, inpType }) {
    return (
        <span>
            <lable className="block text-sm font-medium text-gray-700 mb-2">{lable}</lable>
            <input type={inpType} className="block w-full p-3 border border-gray-300 rounded-lg shadow-sm focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-blue-500 transition duration-200" />
        </span>
    );
}
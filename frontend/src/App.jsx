import { useState } from "react";
import ImageUpload from "./pages/ImageUpload";
import Result from "./pages/Result";

function App() {
    const [result, setResult] = useState(null);

    const handleReset = () => {
        setResult(null);
    };

    return (
        <>
            {!result ? (
                <ImageUpload onResult={setResult} />
            ) : (
                <Result result={result} onReset={handleReset} />
            )}
        </>
    );
}

export default App;
import { useState } from "react";

function ImageUpload({ onResult }) {
    const [image, setImage] = useState(null);
    const [preview, setPreview] = useState(null);
    const [isLoading, setIsLoading] = useState(false);

    const handleImageChange = (e) => {
        const file = e.target.files?.[0];
        if (file) {
            setImage(file);
            setPreview(URL.createObjectURL(file));
        }
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        if (!image) {
            alert("이미지를 선택해주세요!");
            return;
        }

        const formData = new FormData();
        formData.append("image", image);

        try {
            setIsLoading(true);
            const res = await fetch("http://localhost:8080/api/analyze", {
                method: "POST",
                body: formData,
            });

            const result = await res.json();
            onResult(result);
        } catch (err) {
            alert("분석 요청에 실패했습니다.");
        } finally {
            setIsLoading(false);
        }
    };

    return (
        <div className="min-h-screen bg-white px-4 py-8 flex flex-col items-center">
            <h1 className="text-2xl font-bold mb-4">📷 AI 과실 판독기</h1>

            <label className="w-full max-w-sm bg-gray-100 p-4 border border-dashed border-gray-300 rounded-md cursor-pointer mb-4 text-center">
                <input type="file" accept="image/*" onChange={handleImageChange} className="hidden" />
                {preview ? (
                    <img src={preview} alt="미리보기" className="mx-auto max-h-64 rounded-md" />
                ) : (
                    <span className="text-gray-500">카톡 대화 캡처를 업로드해주세요</span>
                )}
            </label>

            <button
                onClick={handleSubmit}
                className="bg-blue-500 text-white px-6 py-2 rounded-md shadow hover:bg-blue-600"
            >
                AI 판정 받기
            </button>

            {isLoading && <p className="mt-4 text-sm text-gray-500">🔍 AI가 과실을 분석 중입니다...</p>}

            {/* 광고 자리 */}
            <div className="mt-10 w-full max-w-sm">
                <div className="bg-yellow-100 border border-yellow-300 p-4 rounded-md text-sm text-center">
                    🔥 광고 자리입니다. <br />
                    (결과 화면에도 표시될 예정)
                </div>
            </div>
        </div>
    );
}

export default ImageUpload;
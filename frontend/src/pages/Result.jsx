import html2canvas from "html2canvas";
import { useRef, useEffect } from "react";
import { Doughnut } from "react-chartjs-2";
import { Chart as ChartJS, ArcElement, Tooltip, Legend } from "chart.js";

ChartJS.register(ArcElement, Tooltip, Legend);

function Result({ result, onReset }) {
    const resultRef = useRef(null);

    useEffect(() => {
        if (!window.Kakao) {
            const script = document.createElement("script");
            script.src = "https://t1.kakaocdn.net/kakao_js_sdk/2.5.0/kakao.min.js";
            script.async = true;
            script.onload = () => {
                window.Kakao.init(import.meta.env.VITE_KAKAO_JS_KEY);
            };
            document.head.appendChild(script);
        } else {
            if (!window.Kakao.isInitialized()) {
                window.Kakao.init(import.meta.env.VITE_KAKAO_JS_KEY);
            }
        }
    }, []);

    const handleDownloadImage = async () => {
        if (!resultRef.current) return;

        const watermark = document.createElement("div");
        watermark.innerText = "blame-detector.ai";
        watermark.style.position = "absolute";
        watermark.style.bottom = "12px";
        watermark.style.right = "12px";
        watermark.style.fontSize = "12px";
        watermark.style.color = "#666";
        watermark.style.zIndex = "9999";
        resultRef.current.appendChild(watermark);

        const canvas = await html2canvas(resultRef.current);
        const link = document.createElement("a");
        link.download = "blame-result.png";
        link.href = canvas.toDataURL("image/png");
        link.click();

        resultRef.current.removeChild(watermark);
    };

    const handleKakaoShare = () => {
        if (window.Kakao && window.Kakao.Share) {
            window.Kakao.Share.sendDefault({
                objectType: "feed",
                content: {
                    title: "AI 과실 분석 결과",
                    description: "이 대화의 과실 비율을 AI가 분석했어요!",
                    imageUrl: "https://blame-detector.vercel.app/og-image.png", // 준비된 대표 이미지
                    link: {
                        mobileWebUrl: "https://blame-detector.vercel.app",
                        webUrl: "https://blame-detector.vercel.app",
                    },
                },
                buttons: [
                    {
                        title: "결과 확인하기",
                        link: {
                            mobileWebUrl: "https://blame-detector.vercel.app",
                            webUrl: "https://blame-detector.vercel.app",
                        },
                    },
                ],
            });
        } else {
            alert("카카오톡 공유 기능을 불러오지 못했습니다.");
        }
    };

    const handleWebShare = async () => {
        if (navigator.share) {
            try {
                await navigator.share({
                    title: "AI 과실 분석기 결과",
                    text: "이 대화의 과실 비율을 AI가 판결했어요!",
                    url: "https://blame-detector.vercel.app"
                });
            } catch (err) {
                alert("공유를 취소하거나 실패했습니다.");
            }
        } else {
            alert("현재 브라우저에서는 공유 기능을 지원하지 않습니다.");
        }
    };

    if (!result) {
        return <div className="p-4 text-center">결과가 없습니다.</div>;
    }

    const { userARatio, userBRatio, reason, analyzedMessages } = result;

    const chartData = {
        labels: ["A", "B"],
        datasets: [
            {
                data: [userARatio, userBRatio],
                backgroundColor: ["#60a5fa", "#f87171"],
                borderWidth: 1,
            },
        ],
    };

    const handleCopyLink = async () => {
        try {
            await navigator.clipboard.writeText("https://blame-detector.vercel.app");
            alert("링크가 복사되었습니다!");
        } catch (err) {
            alert("링크 복사에 실패했습니다.");
        }
    };

    return (
        <div ref={resultRef} className="max-w-2xl mx-auto p-6">
            <h2 className="text-xl font-bold mb-4 text-center">📊 분석 결과</h2>

            <Doughnut data={chartData} className="max-w-xs mx-auto mb-6" />

            <p className="text-gray-700 text-center mb-6">{reason}</p>

            <ul className="space-y-2">
                {analyzedMessages.map((msg, idx) => (
                    <li
                        key={idx}
                        className={`p-3 rounded-md shadow ${msg.author === "A" ? "bg-blue-100" : "bg-red-100"}`}
                    >
                        <p className="font-semibold">{msg.author}</p>
                        <p className="text-sm">{msg.text}</p>
                        <p className="text-xs mt-1 text-gray-500">
                            감정: {msg.emotion} / {msg.toxic ? "공격적" : "온건함"}
                        </p>
                    </li>
                ))}
            </ul>

            {/* 공유 및 다시하기 */}
            <div className="mt-10 flex flex-col items-center gap-4">
                <button
                    onClick={handleKakaoShare}
                    className="bg-yellow-400 text-black px-4 py-2 rounded-md hover:bg-yellow-500"
                >
                    카카오톡으로 공유하기
                </button>

                <button
                    onClick={handleDownloadImage}
                    className="bg-blue-500 text-white px-4 py-2 rounded-md hover:bg-blue-600"
                >
                    결과 이미지 저장
                </button>

                <button
                    onClick={handleCopyLink}
                    className="bg-green-500 text-white px-4 py-2 rounded-md hover:bg-green-600"
                >
                    링크 복사하기
                </button>

                <button
                    onClick={onReset}
                    className="bg-gray-500 text-white px-4 py-2 rounded-md hover:bg-gray-600"
                >
                    다시하기
                </button>

                <div className="bg-yellow-100 border border-yellow-300 p-4 rounded-md text-sm text-center w-full max-w-sm">
                    📢 광고 자리입니다! <br />
                    친구에게 공유하고 나도 결과 보기!
                </div>
            </div>
        </div>
    );
}

export default Result;
# backend_ui

UI（Vue）向けの API を提供するゲートウェイサービスです。  
他のバックエンドサービスへの中継（プロキシ）を担当します。

---

## 📦 役割

- Vue からのリクエストを受け取る
- backend_data_management（9001）などへ RestTemplate で中継
- CORS 設定
- 軽量な API 集約レイヤー

---

## 🔗 主な API

| Method | Path | Description |
|--------|------|-------------|
| GET | `/expired/all` | 9001 の `/expired/all` を中継して返却 |

---

## 🚀 起動
sh start_ui.sh


ポート：**8080**

---

## 🧩 技術

- Spring Boot 3.x
- RestTemplate によるサービス間通信
- backend_common の DTO を利用


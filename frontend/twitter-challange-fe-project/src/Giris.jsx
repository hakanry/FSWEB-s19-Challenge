import axios from "axios";
import "./App.css";
import { useForm } from "react-hook-form";
import { useHistory } from "react-router-dom";

function Login() {
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm();
  const history = useHistory();
  const onSubmit = (data) => {
    axios
      .post("http://localhost:3000/login", data, { withCredentials: true })
      .then((res) => {
        console.log("Giriş başarılı:", res.data);
        history.push("/anasayfa");
      })
      .catch((err) => {
        console.error("Giriş başarısız:", err);
      });
  };

  return (
    <div
      style={{
        maxWidth: "400px",
        margin: "2rem auto",
        padding: "3rem",
        border: "1px solid #ccc",
        borderRadius: "8px",
      }}
    >
      <h2>Giriş Yap</h2>
      <form onSubmit={handleSubmit(onSubmit)}>
        <div style={{ marginBottom: "1rem" }}>
          <label>Kullanıcı Adı:</label>
          <input
            {...register("username", { required: "Kullanıcı adı zorunludur" })}
            type="text"
            style={{ width: "100%", padding: "0.5rem", marginTop: "0.25rem" }}
          />
          {errors.username && (
            <p style={{ color: "red" }}>{errors.username.message}</p>
          )}
        </div>

        <div style={{ marginBottom: "1rem" }}>
          <label>Şifre:</label>
          <input
            {...register("password", { required: "Şifre zorunludur" })}
            type="password"
            style={{ width: "100%", padding: "0.5rem", marginTop: "0.25rem" }}
          />
          {errors.password && (
            <p style={{ color: "red" }}>{errors.password.message}</p>
          )}
        </div>

        <button type="submit" style={{ padding: "0.5rem 1rem" }}>
          Giriş Yap
        </button>
      </form>
    </div>
  );
}

export default Login;

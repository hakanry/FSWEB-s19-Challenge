import axios from "axios";
import "./App.css";
import { useForm } from "react-hook-form";
import { useHistory } from "react-router-dom";

function Kayit() {
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm();
  const history = useHistory();
  const onSubmit = (data) => {
    console.log("Form verileri:", data);
    axios
      .post("http://localhost:3000/register", data)
      .then((res) => console.log(res.data))
      .catch((res) => console.log(res));

    history.push("/giris");
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
      <h2>Kayıt Ol</h2>
      <form onSubmit={handleSubmit(onSubmit)}>
        {/* Kullanıcı Adı */}
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

        {/* Şifre */}
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

        <div style={{ marginBottom: "1rem" }}>
          <label>
            <input
              type="checkbox"
              {...register("isAdmin")}
              style={{ marginRight: "0.5rem" }}
            />
            Admin
          </label>
        </div>

        <button type="submit" style={{ padding: "0.5rem 1rem" }}>
          Kayıt Ol
        </button>
        <button
          onClick={() => history.push("/giris")}
          style={{ padding: "0.5rem 1rem", marginLeft: "1rem" }}
        >
          Giriş Sayfasına Git
        </button>
      </form>
    </div>
  );
}

export default Kayit;

import React, { useState } from "react";
import axios from "axios";
axios.defaults.withCredentials = true;
function Anasayfa() {
  const [users, setUsers] = useState([]);
  const [tweets, setTweets] = useState([]);
  const [commentText, setCommentText] = useState("");
  const [tweetText, setTweetText] = useState("");

  const getUsers = () => {
    axios
      .get("http://localhost:3000/user", { withCredentials: true })
      .then((res) => setUsers(res.data))
      .catch((err) => console.error("User fetch error:", err));
  };

  const deleteUser = (id) => {
    axios
      .delete(`http://localhost:3000/user/${id}`, {
        withCredentials: true,
      })
      .then(() => {
        alert("Kullanıcı silindi");
        getUsers();
      })
      .catch((err) => {
        if (err.response && err.response.status === 403) {
          alert("Bu işlemi yapmaya yetkiniz yok!");
        } else {
          alert("Bir hata oluştu: " + err.message);
        }
        console.error(err);
      });
  };
  const tweet = () => {
    if (!tweetText) return alert("Tweet boş olamaz");
    axios
      .post(
        `http://localhost:3000/tweet`,
        { tweetText: tweetText },
        { withCredentials: true }
      )
      .then(() => alert("Tweet eklendi"));
  };

  const getTweets = () => {
    axios
      .get("http://localhost:3000/tweet", { withCredentials: true })
      .then((res) => setTweets(res.data))
      .catch((err) => console.error("Tweet fetch error:", err));
  };

  const tweetSil = (tweetSilID) => {
    axios
      .delete(`http://localhost:3000/tweet/${tweetSilID}`, {
        withCredentials: true,
      })
      .then(() => {
        alert("Tweet silindi");
        getTweets();
      })
      .catch((err) => {
        if (err.response.status === 400) {
          alert(err.response.data.message);
        } else {
          alert("Bir hata oluştu: " + err.message);
        }
        console.error(err);
      });
  };
  const retweet = (tweetId) => {
    axios
      .post(
        `http://localhost:3000/retweet/${tweetId}`,
        {},
        { withCredentials: true }
      )
      .then(() => {
        alert("Retweet yapıldı");
        getTweets();
      });
  };
  const unRetweet = (tweetId) => {
    axios
      .delete(
        `http://localhost:3000/retweet/${tweetId}`,
        {},
        { withCredentials: true }
      )
      .then(() => {
        alert("Retweet kaldırıldı");
        getTweets();
      });
  };

  const like = (tweetId) => {
    axios
      .post(
        `http://localhost:3000/like/${tweetId}`,
        {},
        { withCredentials: true }
      )
      .then(() => {
        alert("Beğenildi");
        getTweets();
      });
  };
  const dislike = (tweetId) => {
    axios
      .post(
        `http://localhost:3000/dislike/${tweetId}`,
        {},
        { withCredentials: true }
      )
      .then(() => {
        alert("Beğeni kaldırıldı");
        getTweets();
      });
  };

  const comment = (tweetId) => {
    if (!commentText) return alert("Yorum boş olamaz");
    axios
      .post(
        `http://localhost:3000/comment/${tweetId}`,
        { commentText },
        { withCredentials: true }
      )
      .then(() => {
        alert("Yorum eklendi");
        getTweets();
      });
  };
  const logout = () => {
    axios
      .post("http://localhost:3000/logout", {}, { withCredentials: true })
      .then(() => {
        alert("Çıkış yapıldı");
      })
      .catch((err) => {
        console.error("Çıkış hatası:", err);
      });
  };
  return (
    <div style={{ padding: "2rem", background: "#111", color: "white" }}>
      <div>
        <button onClick={() => logout()}>Çıkış Yap</button>
      </div>

      <hr style={{ margin: "2rem" }} />

      <div>
        <input
          type="text"
          placeholder="Tweet'ini buraya yaz"
          value={tweetText}
          onChange={(e) => setTweetText(e.target.value)}
          style={{ marginLeft: "10px" }}
        />
        <button onClick={() => tweet()}>Tweet At</button>
      </div>

      <hr style={{ margin: "2rem" }} />

      <button onClick={getUsers}>Kullanıcıları Getir</button>
      <div>
        {users.map((user, indexx) => (
          <div
            key={indexx}
            style={{
              margin: "10px",
              border: "1px solid white",
              padding: "10px",
            }}
          >
            <strong>
              {user.username}(ID: {user.id})
            </strong>
            <button
              onClick={() => deleteUser(user.id)}
              style={{ marginLeft: "10px" }}
            >
              Sil
            </button>
          </div>
        ))}
      </div>

      <hr style={{ margin: "2rem" }} />

      <button onClick={getTweets}>Tweetleri Getir</button>
      <div>
        {tweets.map((tweet, i) => (
          <div
            key={i}
            style={{
              margin: "10px",
              border: "1px solid white",
              padding: "10px",
            }}
          >
            <p>
              <strong>{tweet.user}</strong>: {tweet.tweetText}
            </p>
            <div>
              <p>RETWEETS:{tweet.reTweets.length}</p>
              <p>LIKES:{tweet.likes.length}</p>
              {tweet.comments.length !== 0 && (
                <div>
                  COMMENTS:
                  {tweet.comments.map((c, i) => (
                    <div key={i}>
                      <strong>{c.user}:</strong> {c.commentText}
                    </div>
                  ))}
                </div>
              )}
            </div>
            <button onClick={() => retweet(tweet.id)}>Retweet</button>
            <button onClick={() => unRetweet(tweet.id)}>unRetweet</button>
            <button onClick={() => like(tweet.id)}>Beğen</button>
            <button onClick={() => dislike(tweet.id)}>Beğenme</button>
            <button onClick={() => tweetSil(tweet.id)}>Tweeti Sil</button>
            <div>
              <input
                type="text"
                placeholder="Yorum yaz"
                value={commentText}
                onChange={(e) => setCommentText(e.target.value)}
                style={{ marginLeft: "10px" }}
              />
              <button onClick={() => comment(tweet.id)}>Yorum Yap</button>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}

export default Anasayfa;

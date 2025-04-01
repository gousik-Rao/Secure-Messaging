
function checkError(){
	// Check if there's an error in the query string
	const urlParams = new URLSearchParams(window.location.search);
	if (urlParams.has('error') && urlParams.get('error') === '1') {
	    alert("Invalid Credentials! Please try again.");
	}
}

function closePopup(){
	document.getElementById("overlay").style.display = "none";
}

// Show the respective form based on the button clicked
function showForm(formType) {
    // Hide all forms
    const forms = document.querySelectorAll('.form-section');
    forms.forEach(form => form.classList.add('hidden'));

    // Show the selected form
    const formToShow = document.getElementById(formType);
    if (formToShow) {
        formToShow.classList.remove('hidden');
    }
}

// Sidebar toggle logic
const openbar = document.getElementById("menutoggle");
const sidebar = document.querySelector(".sidebar");
const closebar = document.getElementById("closebar");
const backdrop = document.getElementById("sidebarBackdrop");

// Opening the sidebar, icon
openbar.addEventListener("click", function(e){
	e.preventDefault();
	console.log("Menu toggle clicked");
	sidebar.classList.add("active");
	backdrop.classList.add("active");
});
// Closing the sidebar, icon
closebar.addEventListener("click", function(e){
	e.preventDefault();
	console.log("Close bar clicked");
	sidebar.classList.remove("active");
    backdrop.classList.remove("active");
});

// Close Sidebar and Hide Backdrop (when clicking on Backdrop)
backdrop.addEventListener("click", function () {
    sidebar.classList.remove("active");
    backdrop.classList.remove("active");
});

// Header scroll
window.addEventListener('scroll', () => {
    const header = document.querySelector('header');
    if (window.scrollY > 50) {
        header.classList.add('scrolled');
    } else {
        header.classList.remove('scrolled');
    }
});

// Display popup if errorMessage exists
document.addEventListener("DOMContentLoaded", () => {
	// Fetch the error message from a data attribute
	const errorSections = document.querySelectorAll("[data-error-message]");
    
	errorSections.forEach(section => {
		const errorMessage = section.dataset.errorMessage; // Getting the error message          
		if(errorMessage){
			alert(errorMessage); // Display error message in a popup
		}
	});
});

/* Preventing default form submission */
// const login_form = document.getElementById("login-form");
// login_form.addEventListner("DOMContentLoaded", (e) => {
// 	e.preventDefault();
// });

// Scroll effect
document.addEventListener('DOMContentLoaded', function () {
    console.log("JavaScript Loaded");

    const images = document.querySelectorAll('.animated-image');

    if (!images.length) {
        console.error("No images found to observe");
        return;
    }

    // Check visibility on page load
    const isElementInViewport = (el) => {
        const rect = el.getBoundingClientRect();
        return (
            rect.top >= 0 &&
            rect.left >= 0 &&
            rect.bottom <= (window.innerHeight || document.documentElement.clientHeight) &&
            rect.right <= (window.innerWidth || document.documentElement.clientWidth)
        );
    };

    images.forEach(image => {
        // If already visible on page load, add the 'show' class
        if (isElementInViewport(image)) {
            image.classList.add('show');
        }
    });

    // IntersectionObserver for handling scroll
    const observer = new IntersectionObserver(entries => {
        entries.forEach(entry => {
            if (entry.isIntersecting) {
                entry.target.classList.add('show'); // Add animation when visible
            } else {
                entry.target.classList.remove('show'); // Remove animation when hidden
            }
        });
    });

    // Observe each image
    images.forEach(image => {
        observer.observe(image);
    });

    console.log("Observer initialized for images.");
});

window.onload = function() {
    var errorMessage = "<%= errorMessage %>";
    if (errorMessage) {
        document.getElementById("overlay").style.display = "block";
    }
};


// Function to handle form submission
function sendMessage(event) {
    event.preventDefault(); // Prevent the default form submission

    // Gather form data
    const form = document.querySelector("message-form");
    const formData = new FormData(form);

    // Send an AJAX request to the server
    fetch(form.action, {
        method: "POST",
        body: formData,
    })
    .then(response => response.text())
    .then(message => {
        // Display the popup with the response message
        alert(message);
    })
    .catch(error => {
        console.error("Error sending message:", error);
        alert("An error occurred while sending the message.");
    });
}

document.getElementById("loginButton").addEventListener("click", function() {
    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;

    fetch("/api/auth/login", {
		method:"POST",
		headers: {"Content-Type": "application/json"},
		body: JSON.stringify({email, password})
	}).then(response => response.json())
	.then(data => {
		if(data.status === "success"){
			alert("Login Successful!")
			// Redirect to home page after successful login
			window.location.href = "/home/html";
		}else { 
			alert(data.message); // Showing error message
		}
	})
	.catch(error => console.error("Error: ", error));
});













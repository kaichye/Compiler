.data
.comm	a,4,4

.comm	b,4,4

.text
	.align 4
.globl  func
func:
func_bb2:
func_bb3:
	movl	$123, %EAX
	movl	%ESI, %EAX
func_bb4:
	ret
.globl  main
main:
main_bb2:
	pushq	%R15
main_bb3:
	movl	$1, %EAX
	movl	%EAX, %EDI
	movl	$2, %EAX
	movl	%EAX, %ESI
	call	func
	movl	$5, %EAX
	cmpl	%EAX, %R15D
	jge	main_bb5
main_bb4:
	movl	$1, %EAX
	movl	%R15D, %EDI
	movl	$5, %EAX
	cmpl	%EAX, %R15D
	jl	main_bb4
main_bb5:
	movl	$1, %EAX
	cmpl	%EAX, %R15D
	jne	main_bb8
main_bb6:
	movl	$2, %EAX
	cmpl	%EAX, %R15D
	jne	main_bb11
main_bb9:
main_bb7:
main_bb18:
	popq	%R15
	ret
main_bb11:
	jmp	main_bb7
main_bb14:
	jmp	main_bb13
main_bb17:
	jmp	main_bb7
main_bb8:
	movl	$3, %EAX
	cmpl	%EAX, %R15D
	jne	main_bb14
main_bb12:
main_bb13:
	movl	$100, %EAX
	cmpl	%EAX, %R15D
	jne	main_bb17
main_bb15:
main_bb16:
	jmp	main_bb7
